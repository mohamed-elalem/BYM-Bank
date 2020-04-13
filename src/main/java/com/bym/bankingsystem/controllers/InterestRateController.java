package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.setting.InterestRate;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.services.IInterestRateService;
import com.bym.bankingsystem.services.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/interest-rate")
public class InterestRateController {
    @Autowired
    IInterestRateService interestRateService;

    @GetMapping
    public Response findAll(){
        List<InterestRate> interestRate = this.interestRateService.getAllInterestRates();
        return new Response(200,"succeed",interestRate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestRate> findInterestRateById(@PathVariable("id") Long id){
        Optional<InterestRate> interestRate = interestRateService.getSingleInterestRate(id);
        if(interestRate.isPresent()){
            return ResponseEntity.ok(interestRate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addNewInterestRate(@RequestBody InterestRate interestRateToBeAdded) {
        interestRateService.save(interestRateToBeAdded);

    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInterestRate(@RequestBody InterestRate interestRateToBeAdded) {
        interestRateService.update(interestRateToBeAdded);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTransactionType(@PathVariable("id") Long id) {
        interestRateService.delete(id);

    }
}
