package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.setting.InterestRate;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.services.IInterestRateService;
import com.bym.bankingsystem.services.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest-rate")
public class InterestRateController {
    @Autowired
    IInterestRateService interestRateService;

    @GetMapping
    public Response findAll(){
        List<InterestRate> interestRate = this.interestRateService.getAllInterestRates();
        return new Response(200,"succeed",interestRate);
    }

    @GetMapping("/{id}")
    public InterestRate findInterestRateById(@PathVariable("id") Long id){
        return interestRateService.getSingleInterestRate(id);
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
