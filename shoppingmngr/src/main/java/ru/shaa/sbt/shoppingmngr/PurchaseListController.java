package ru.shaa.sbt.shoppingmngr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;
import ru.shaa.sbt.shoppingmngr.repositories.IPurchaseListRepository;

import java.util.List;

@RestController
@RequestMapping("/purchaselist")
public class PurchaseListController {
    IPurchaseListRepository purchaseListRepository;

    @Autowired
    public PurchaseListController(IPurchaseListRepository purchaseListRepository)
    {
        this.purchaseListRepository = purchaseListRepository;
    }

    @GetMapping("/{id:[0-9]+}")
    public PurchaseList getPurchaseList(@PathVariable("id") int idList) {
        return purchaseListRepository.getById(idList);
    }

    @GetMapping("/{id:[0-9]+}/planned/{idPlannedPurchase:[0-9]+}")
    public Object getPlannedPurchase(@PathVariable("id") int idList, @PathVariable("idPlannedPurchase") Integer idPlannedPurchase) {
        Object result = null;
        if (idPlannedPurchase == null)
            result = purchaseListRepository.getById(idList).getPlannedPurchases();
        else
            for (PlannedPurchase p:
                    purchaseListRepository.getById(idList).getPlannedPurchases()) {
                if (p.getId() == idPlannedPurchase)
                    result = p;
            }

        return result;
    }

    @GetMapping("/{id:[0-9]+}/planned")
    public List<PlannedPurchase> getPlannedPurchaseList(@PathVariable("id") int idList) {
        return purchaseListRepository.getById(idList).getPlannedPurchases();
    }

}
