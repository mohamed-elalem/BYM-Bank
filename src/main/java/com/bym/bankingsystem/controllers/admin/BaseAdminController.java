package com.bym.bankingsystem.controllers.admin;

import com.bym.bankingsystem.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin"})
public abstract class BaseAdminController extends BaseController {
}
