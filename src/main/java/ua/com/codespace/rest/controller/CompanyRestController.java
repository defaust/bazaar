package ua.com.codespace.rest.controller;

import ua.com.codespace.model.Company;
import ua.com.codespace.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Company> getCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }
}
