package com.kishko.userservice.controllers;

import com.kishko.userservice.dtos.AdvancedStockDTO;
import com.kishko.userservice.dtos.StockDTO;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.services.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@CrossOrigin
@Tag(name="Акция-контроллер", description="Методы для взаимодействия с акциями")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    @Operation(
            summary = "Получить список всех акций"
    )
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @GetMapping("/name")
    @Operation(
            summary = "Получить акцию по ее названию"
    )
    public ResponseEntity<StockDTO> getStockByName(@RequestParam("name") String name) throws Exception {
        return new ResponseEntity<>(stockService.getStockByName(name), HttpStatus.OK);
    }

    @GetMapping("/{stockId}")
    @Operation(
            summary = "Получить акцию по ее ID"
    )
    public ResponseEntity<StockDTO> getStockById(@PathVariable("stockId") Long stockId) throws Exception {
        return new ResponseEntity<>(stockService.getStockById(stockId), HttpStatus.OK);
    }

    @GetMapping("/advancedStock/{advancedStockId}")
    @Operation(
            summary = "Получить advanced акцию по ее ID"
    )
    public ResponseEntity<AdvancedStockDTO> getAdvancedStockById(@PathVariable("advancedStockId") Long advancedStockId) throws Exception {
        return new ResponseEntity<>(stockService.getAdvancedStockById(advancedStockId), HttpStatus.OK);
    }

    @GetMapping("/advanced/{userId}")
    @Operation(
            summary = "Получить все акции пользователя и их количество по его ID"
    )
    public ResponseEntity<List<AdvancedStockDTO>> getAdvancedStocksByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(stockService.getAdvancedStocksByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление данных акции по его ID",
            description = "Предоставляется возможность обновить данные акции в формате JSON."
    )
    public ResponseEntity<StockDTO> updateStockById(@PathVariable("id") Long id, @RequestBody Stock stock) throws Exception {
        return new ResponseEntity<>(stockService.updateStockById(id, stock), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать новую акцию"
    )
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) {
        return new ResponseEntity<>(stockService.createStock(stockDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockId}")
    @Operation(
            summary = "Удалить акцию по ID"
    )
    public ResponseEntity<String> deleteStockById(@PathVariable("stockId") Long stockId) throws UserNotFoundException {
        return new ResponseEntity<>(stockService.deleteStockById(stockId), HttpStatus.OK);
    }

    @PutMapping("/{stockId}/change/photo")
    @Operation(
            summary = "Изменение фотографии акции",
            description = "Для реализации необходимо передать в URL ID акции и выбрать фотографию. Старая фотография при этом удаляется. "
    )
    public ResponseEntity<StockDTO> changeStockPhoto(@PathVariable("stockId") Long stockId, @RequestParam("photo") MultipartFile photo) throws Exception {
        return new ResponseEntity<>(stockService.changeStockPhoto(stockId, photo), HttpStatus.OK);
    }

    @GetMapping("/profit/advancedStock/{advancedStockId}")
    @Operation(
            summary = "Получить прибыль AdvancedStock по ее ID"
    )
    public ResponseEntity<Double> getProfitByAdvancedStockId(@PathVariable("advancedStockId") Long advancedStockId) throws Exception {
        return new ResponseEntity<>(stockService.getProfitByAdvancedStockId(advancedStockId), HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<Mono<Double>> getCurrentPriceOfStock(@RequestParam("shortName") String shortName) {
        return new ResponseEntity<>(stockService.getCurrentPriceOfStock(shortName), HttpStatus.OK);
    }

}
