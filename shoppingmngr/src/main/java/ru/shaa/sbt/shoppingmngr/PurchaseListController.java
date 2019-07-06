package ru.shaa.sbt.shoppingmngr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;
import ru.shaa.sbt.shoppingmngr.repositories.IPlannedPurchaseRepository;
import ru.shaa.sbt.shoppingmngr.repositories.IPurchaseListRepository;

import java.util.List;

@RestController
@RequestMapping("/purchaselist")
public class PurchaseListController {
    IPurchaseListRepository purchaseListRepository;
    IPlannedPurchaseRepository plannedPurchaseRepository;

    @Autowired
    public PurchaseListController(IPurchaseListRepository purchaseListRepository, IPlannedPurchaseRepository plannedPurchaseRepository)
    {
        this.purchaseListRepository = purchaseListRepository;
        this.plannedPurchaseRepository = plannedPurchaseRepository;
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

    @DeleteMapping("/{id:[0-9]+}/planned/{idPlannedPurchase:[0-9]+}")
    public ResponseEntity<PlannedPurchase> deletePlannedPurchase(@PathVariable("id") int idList, @PathVariable("idPlannedPurchase") Integer idPlannedPurchase) {
        PlannedPurchase result = null;
        for (PlannedPurchase p:
            purchaseListRepository.getById(idList).getPlannedPurchases()) {
            if (p.getId() == idPlannedPurchase)
                result = p;
        }

        if (result == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        plannedPurchaseRepository.delete(result);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @GetMapping("/{id:[0-9]+}/planned")
    public List<PlannedPurchase> getPlannedPurchaseList(@PathVariable("id") int idList) {
        return purchaseListRepository.getById(idList).getPlannedPurchases();
    }

}
