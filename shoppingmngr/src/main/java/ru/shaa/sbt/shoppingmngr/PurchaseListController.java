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
    public ResponseEntity<PurchaseList> getPurchaseList(@PathVariable("id") int idList) {
        PurchaseList purchaseList = purchaseListRepository.getById(idList);
        if (purchaseList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(purchaseList);
    }

    @GetMapping("/{id:[0-9]+}/planned/{idPlannedPurchase:[0-9]+}")
    public ResponseEntity<Object> getPlannedPurchase(@PathVariable("id") int idList, @PathVariable("idPlannedPurchase") Integer idPlannedPurchase) {
        List<PlannedPurchase>  plannedPurchaseList = purchaseListRepository.getById(idList).getPlannedPurchases();
        PlannedPurchase plannedPurchase = null;

        if (plannedPurchaseList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        if (idPlannedPurchase == null)
            return ResponseEntity.status(HttpStatus.OK).body(plannedPurchaseList);

        for (PlannedPurchase p:
                    plannedPurchaseList) {
            if (p.getId() == idPlannedPurchase)
                plannedPurchase = p;
        }

        if (plannedPurchase == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(plannedPurchase);
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
