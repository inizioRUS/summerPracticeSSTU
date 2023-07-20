package com.kishko.userservice.services;

import com.kishko.userservice.dtos.AdvancedStockDTO;
import com.kishko.userservice.dtos.StockDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.errors.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StockService {
    List<StockDTO> getAllStocks();

    StockDTO getStockByName(String name) throws Exception;

    StockDTO getStockById(Long stockId) throws Exception;

    List<AdvancedStockDTO> getAdvancedStocksByUserId(Long userId);

    StockDTO updateStockById(Long id, Stock stock) throws Exception;

    StockDTO createStock(StockDTO stock);

    String deleteStockById(Long stockId) throws UserNotFoundException;

    AdvancedStock toAdvancedStock(AdvancedStockDTO advancedStockDTO) throws Exception;

    AdvancedStockDTO toDTO(AdvancedStock advancedStock);

    StockDTO changeStockPhoto(Long stockId, MultipartFile photo) throws Exception;

    Stock toStock(StockDTO stockDTO);

    StockDTO toDTO(Stock stock);

    Double getProfitByAdvancedStockId(Long advancedStockId) throws Exception;

    Mono<Double> getCurrentPriceOfStock(String shortName);
}
