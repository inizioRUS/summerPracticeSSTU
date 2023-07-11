package com.kishko.userservice.services;

import com.kishko.photoservice.repositories.AttachmentRepository;
import com.kishko.photoservice.services.AttachmentService;
import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.AdvancedStockRepository;
import com.kishko.userservice.repositories.StockRepository;
import com.kishko.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdvancedStockRepository advancedStockRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private AttachmentService attachmentService;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO userDTO;
    private UserDTO userDTO2;

    private Stock stock;

    @BeforeEach
    void setUp() {

        userService = new UserServiceImpl(userRepository, advancedStockRepository, stockRepository, attachmentRepository, attachmentService);

        stock = Stock.builder()
                .id(1L)
                .name("Газпром")
                .price(1500.0)
                .build();

        userDTO = UserDTO.builder()
                .id(1L)
                .name("Dima")
                .surname("Kishko")
                .email("kishko.2003@list.ru")
                .password("amirochka05")
                .role(Role.USER)
                .balance(15000.0)
                .advancedStocks(null)
                .wishlist(List.of(stock, stock))
                .build();

        userDTO2 = UserDTO.builder()
                .id(2L)
                .name("Dima")
                .surname("Kishko")
                .email("kishko.2004@list.ru")
                .password("amirochka05")
                .role(Role.USER)
                .balance(15000.0)
                .advancedStocks(null)
                .build();

    }

    @Test
    void getAllUsers() {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.findAll()).thenReturn(
                List.of(toUser(userDTO), toUser(userDTO2)));

        assertEquals(userService.getAllUsers().get(0).getEmail(), userDTO.getEmail());
        assertEquals(userService.getAllUsers().get(1).getEmail(), userDTO2.getEmail());

    }

    @Test
    void getUserById() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.of(toUser(userDTO)));

        UserDTO savedUser = userService.getUserById(1L);

        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getEmail(), userDTO.getEmail());

    }

    @Test
    void getUserByEmail() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.getUserByEmail(userDTO.getEmail())).thenReturn(Optional.of(toUser(userDTO)));

        UserDTO savedUser = userService.getUserByEmail(userDTO.getEmail());

        assertThat(savedUser).isNotNull();

    }

    @Test
    void updateUserById() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.save(any(User.class))).thenReturn(toUser(userDTO));

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(toUser(userDTO)));

        UserDTO savedUser = userService.updateUserById(userDTO.getId(), userDTO);

        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getEmail(), userDTO.getEmail());

    }

    @Test
    void deleteUserById() {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.of(toUser(userDTO)));

        assertAll(() -> userService.deleteUserById(1L));

    }

    @Test
    void increaseUserBalance() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        Double amount = 1000.0;

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.ofNullable(toUser(userDTO)));

        UserDTO savedUser = userService.increaseUserBalance(userDTO.getId(), amount);

        System.out.println(savedUser);

        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getBalance(), userDTO.getBalance() + amount);

    }

    @Test
    void decreaseUserBalance() throws Exception {

        mock(UserDTO.class);
        mock(UserRepository.class);

        Double amount = 1000.0;

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.ofNullable(toUser(userDTO)));

        UserDTO savedUser = userService.decreaseUserBalance(userDTO.getId(), amount);

        System.out.println(savedUser);

        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getBalance(), userDTO.getBalance() - amount);

    }

    @Test
    @Disabled
    void addUserWishlistStock() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);
        mock(StockRepository.class);
        mock(Stock.class);
        mock(UserServiceImpl.class);

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.ofNullable(toUser(userDTO)));
        when(stockRepository.findById(stock.getId())).thenReturn(Optional.of(stock));

        UserDTO savedUser = userService.addUserWishlistStock(userDTO.getId(), stock.getId());

        System.out.println(savedUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getWishlist()).isNotNull();

    }

    @Test
    @Disabled
    void deleteUserWishlistStock() throws Exception {

        mock(UserDTO.class);
        mock(UserRepository.class);
        mock(StockRepository.class);
        mock(Stock.class);

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(toUser(userDTO)));
        when(stockRepository.findById(stock.getId())).thenReturn(Optional.of(stock));

        System.out.println(userDTO);

        UserDTO savedUser = userService.deleteUserWishlistStock(userDTO.getId(), stock.getId());

        System.out.println(savedUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getWishlist()).isNotNull();

    }

    public User toUser(UserDTO userDTO) {

        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .balance(userDTO.getBalance())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .advancedStocks(userDTO.getAdvancedStocks())
                .build();
    }
}