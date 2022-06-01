package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.AdvertisedStatus;
import com.ineri.ineri_lk.service.impl.AdvertisedStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Slotin Alexander (@alexsnitol)
 */
@Controller
@RequestMapping("/advertised-statuses")
public class AdvertisedStatusController {

    @Autowired
    private AdvertisedStatusServiceImpl advertisedStatusService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_advertised_statuses");

        mv.addObject("advertisedStatuses", advertisedStatusService.getAll());

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newAdvertisedStatus(AdvertisedStatus advertisedStatus) {
        return new ModelAndView("new_advertised_status");
    }

    @PostMapping("/new")
    public String createAdvertisedStatus(AdvertisedStatus advertisedStatus) {
        advertisedStatusService.save(advertisedStatus);
        return "redirect:/advertised-statuses";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        advertisedStatusService.deleteById(id);
        return "redirect:/advertised-statuses";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit_advertised_status");
        mv.addObject(advertisedStatusService.getById(id));
        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(AdvertisedStatus advertisedStatus) {
        advertisedStatusService.save(advertisedStatus);
        return "redirect:/advertised-statuses";
    }

}
