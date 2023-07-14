package com.kishko.userservice.services;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kishko.photoservice.entities.Attachment;
import com.kishko.photoservice.repositories.AttachmentRepository;
import com.kishko.photoservice.services.AttachmentService;
import com.kishko.userservice.dtos.AdvancedStockDTO;
import com.kishko.userservice.dtos.StockDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.AdvancedStockRepository;
import com.kishko.userservice.repositories.StockRepository;
import com.kishko.userservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AdvancedStockRepository advancedStockRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentService attachmentService;


    @Override
    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(this::toDTO).toList();
    }

    @Override
    public StockDTO getStockByName(String name) throws Exception {

        Stock stock = stockRepository.findStockByName(name);

        if (stock == null) throw new Exception("There is no stock with name: " + name);

        return toDTO(stock);
    }

    @Override
    public StockDTO getStockById(Long stockId) throws Exception {

        Stock stock = stockRepository.findById(stockId).orElseThrow(
                () -> new Exception("There is no stock with id: " + stockId)
        );

        return toDTO(stock);
    }

    @Transactional
    @Override
    public List<AdvancedStockDTO> getAdvancedStocksByUserId(Long userId) {
        return advancedStockRepository.findAdvancedStocksByUserId(userId).stream()
                .map(this::toDTO).toList();
    }

    @Override
    public StockDTO updateStockById(Long id, Stock stock) throws Exception {

        StockDTO stockDB = getStockById(id);

        String name = stock.getName();

        Double price = stock.getPrice();

        if (Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            stockDB.setName(name);
        }

        if (Objects.nonNull(price) && price > 0) {
            stockDB.setPrice(price);
        }

        stockRepository.save(toStock(stockDB));

        return stockDB;
    }

    @Override
    @Transactional
    public StockDTO changeStockPhoto(Long stockId, MultipartFile photo) throws Exception {

        StockDTO stock = getStockById(stockId);
        Attachment attachment;

        if (photo.getSize() != 0) {

            if (!"a9a735f5-ec07-4ca6-90e2-852ab63318a7".equals(stock.getAttachmentId()) && stock.getAttachmentId() != null) {
                attachmentRepository.deleteById(stock.getAttachmentId());
            }

            attachment = attachmentService.saveAttachment(photo);

            stock.setAttachmentId(attachment.getId());

            stockRepository.save(toStock(stock));

        }

        return stock;
    }

    @Override
    public StockDTO createStock(StockDTO stockDTO) {

        stockRepository.save(toStock(stockDTO));

        return stockDTO;
    }

    @Override
    public String deleteStockById(Long stockId) throws UserNotFoundException {

        if (stockRepository.findById(stockId).isEmpty()) {
            throw new UserNotFoundException("There is no stock with id: " + stockId);
        }

        userRepository.deleteById(stockId);

        return "successful deleted";
    }

    @Override
    public AdvancedStock toAdvancedStock(AdvancedStockDTO advancedStockDTO) throws Exception {

        StockDTO stock = getStockById(advancedStockDTO.getStockId());
        User user = userRepository.findById(advancedStockDTO.getUserId()).orElseThrow(
                () -> new UserNotFoundException("There is no user with id: " + advancedStockDTO.getUserId())
        );

        return AdvancedStock.builder()
                .id(advancedStockDTO.getId())
                .stock(toStock(stock))
                .user(user)
                .count(advancedStockDTO.getCount())
                .build();
    }

    @Override
    public AdvancedStockDTO toDTO(AdvancedStock advancedStock) {
        return AdvancedStockDTO.builder()
                .id(advancedStock.getId())
                .stockId(advancedStock.getStock().getId())
                .userId(advancedStock.getUser().getId())
                .count(advancedStock.getCount())
                .build();
    }

    @Override
    public Stock toStock(StockDTO stockDTO) {

        String attachmentId;

        if (stockDTO.getAttachmentId() == null || stockDTO.getAttachmentId().equals("string")) {
            attachmentId = null;
        } else attachmentId = stockDTO.getAttachmentId();

        return Stock.builder()
                .id(stockDTO.getId())
                .name(stockDTO.getName())
                .price(stockDTO.getPrice())
                .advancedStocks(stockDTO.getAdvancedStocks())
                .users(stockDTO.getUsers())
                .attachmentId(attachmentId)
                .build();
    }

    @Override
    public StockDTO toDTO(Stock stock) {

        return StockDTO.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .advancedStocks(stock.getAdvancedStocks())
                .users(stock.getUsers())
                .attachmentId(stock.getAttachmentId())
                .build();
    }

}
