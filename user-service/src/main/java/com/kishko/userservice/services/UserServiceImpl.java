package com.kishko.userservice.services;

import com.kishko.photoservice.entities.Attachment;
import com.kishko.photoservice.repositories.AttachmentRepository;
import com.kishko.photoservice.services.AttachmentService;
import com.kishko.userservice.dtos.AdvancedStockDTO;
import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.AdvancedStockRepository;
import com.kishko.userservice.repositories.StockRepository;
import com.kishko.userservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvancedStockRepository advancedStock;

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO).toList();
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("There is no user with id: " + id)
        );

        return toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException {

        User user = userRepository.getUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException("There is no user with email: " + email)
        );

        return toDTO(user);
    }

    @Override
    public UserDTO updateUserById(Long id, UserDTO userDTO) throws UserNotFoundException {

        UserDTO userDB = getUserById(id);

        String email = userDTO.getEmail();

        String password = userDTO.getPassword();

        String name = userDTO.getName();

        String surname = userDTO.getSurname();

        if (Objects.nonNull(email) && !"".equalsIgnoreCase(email)) {
            userDB.setEmail(email);
        }

//        if (Objects.nonNull(password) && !"".equalsIgnoreCase(password)) {
//            userDB.setPassword(new BCryptPasswordEncoder().encode(password));
//        }

        if (Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            userDB.setName(name);
        }

        if (Objects.nonNull(surname) && !"".equalsIgnoreCase(surname)) {
            userDB.setSurname(surname);
        }

        userRepository.save(toUser(userDB));

        return userDB;
    }

    @Override
    public String deleteUserById(Long id) throws UserNotFoundException {

        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("There is no user with id: " + id);
        }

        userRepository.deleteById(id);

        return "successful deleted";
    }

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

    @Override
    public UserDTO updateUserStocks(Long userId, Long stockId, Integer count) throws Exception {

        UserDTO user = getUserById(userId);

        AdvancedStock advancedStockDB = advancedStock.getAdvancedStockByUserIdAndStockId(userId, stockId);

        if (advancedStockDB == null) {

            advancedStockDB = AdvancedStock.builder()
                    .count(0)
                    .stock(stockRepository.findById(stockId).orElseThrow(
                            () -> new Exception("There is no such stock with id: " + stockId)
                    ))
                    .user(userRepository.findById(userId).orElseThrow(
                            () -> new Exception("There is no such user with id: " + userId)
                    ))
                    .build();

        }

        int currentCount = advancedStockDB.getCount();

        advancedStockDB.setCount(currentCount + count);

        advancedStock.save(advancedStockDB);

        return user;
    }

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

    @Override
    public UserDTO deleteUserStocks(Long userId, Long stockId, Integer count) throws Exception {

        UserDTO user = getUserById(userId);

        AdvancedStock advancedStockDB = advancedStock.getAdvancedStockByUserIdAndStockId(userId, stockId);

        if (advancedStockDB == null) throw new Exception("There is no such advancedStock");

        int currentCount = advancedStockDB.getCount();

        advancedStockDB.setCount(currentCount - count);

        if (advancedStockDB.getCount() == 0) {

            advancedStock.deleteById(advancedStockDB.getId());

        } else {

            advancedStock.save(advancedStockDB);

        }

        return user;

    }

    @Override
    public UserDTO addUserWishlistStock(Long userId, Long stockId) throws UserNotFoundException {

        List<Stock> stocks;
        UserDTO userDB = getUserById(userId);
        Stock stock = stockRepository.findById(stockId).get();

        stocks = userDB.getWishlist();

        stocks.add(stock);
        userDB.setWishlist(stocks);

        userRepository.save(toUser(userDB));

        return userDB;

    }

    @Override
    public UserDTO deleteUserWishlistStock(Long userId, Long stockId) throws Exception {

        List<Stock> stocks;
        UserDTO userDB = getUserById(userId);
        Stock stock = stockRepository.findById(stockId).get();

        if (userDB.getWishlist().isEmpty()) {
            throw new Exception("User with id: " + userId + " has an empty wishlist.");
        }

        stocks = userDB.getWishlist();

        stocks.remove(stock);

        userDB.setWishlist(stocks);

        userRepository.save(toUser(userDB));

        return userDB;

    }

    @Override
    @Transactional
    public UserDTO changeUserPhoto(Long userId, MultipartFile photo) throws Exception {

        UserDTO userDTO = getUserById(userId);
        Attachment attachment;

        if (photo.getSize() != 0) {

            if (!userDTO.getAttachmentId().equals("a9a735f5-ec07-4ca6-90e2-852ab63318a7")) attachmentRepository.deleteAttachmentByUserId(userDTO.getId());

            attachment = attachmentService.saveAttachment(photo, userId);

            userDTO.setAttachmentId(attachment.getId());

            userRepository.save(toUser(userDTO));

        }

        return userDTO;
    }

    @Override
    public UserDTO increaseUserBalance(Long userId, Double amount) throws UserNotFoundException {

        UserDTO user = getUserById(userId);

        Double balance = user.getBalance();

        user.setBalance(balance + amount);

        return user;
    }

    @Override
    public UserDTO decreaseUserBalance(Long userId, Double amount) throws Exception {

        UserDTO user = getUserById(userId);

        Double balance = user.getBalance();

        double action = balance - amount;

        if (action >= 0) user.setBalance(action);
        else {
            throw new Exception("This user haven't quite amount of money to confirm payment");
        }

        return user;
    }

    @Override
    public UserDTO toDTO(User user) {

        String attachmentId;

        if (user.getAttachment() == null) {
            attachmentId = "a9a735f5-ec07-4ca6-90e2-852ab63318a7";
        } else attachmentId = user.getAttachment().getId();

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .balance(user.getBalance())
                .name(user.getName())
                .surname(user.getSurname())
                .advancedStocks(user.getAdvancedStocks())
                .wishlist(user.getWishlist())
                .attachmentId(attachmentId)
                .build();
    }

    @Override
    public User toUser(UserDTO userDTO) {

        Attachment attachment = attachmentRepository.findById(userDTO.getAttachmentId()).orElse(
                attachmentRepository.findById("a9a735f5-ec07-4ca6-90e2-852ab63318a7").get()
        ); // НА ВСЯКИЙ СЛУЧАЙ

        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .balance(userDTO.getBalance())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .advancedStocks(userDTO.getAdvancedStocks())
                .wishlist(userDTO.getWishlist())
                .attachment(attachment)
                .build();
    }

}
