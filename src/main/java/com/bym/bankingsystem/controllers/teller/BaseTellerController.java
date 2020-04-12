package com.bym.bankingsystem.controllers.teller;

import com.bym.bankingsystem.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/teller"})
public abstract class BaseTellerController extends BaseController {
}
