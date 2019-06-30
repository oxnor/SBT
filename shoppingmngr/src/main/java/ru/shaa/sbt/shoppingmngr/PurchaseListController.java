package ru.shaa.sbt.shoppingmngr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;
import ru.shaa.sbt.shoppingmngr.repositories.IPurchaseListRepository;

@RestController
@RequestMapping("/purchaselist")
public class PurchaseListController {
    IPurchaseListRepository purchaseListRepository;

    @Autowired
    public PurchaseListController(IPurchaseListRepository purchaseListRepository)
    {
        this.purchaseListRepository = purchaseListRepository;
    }

    @GetMapping
    public PurchaseList getPurchaseList(@RequestParam int Id_List) {
        return purchaseListRepository.getById(Id_List);
    }
}
