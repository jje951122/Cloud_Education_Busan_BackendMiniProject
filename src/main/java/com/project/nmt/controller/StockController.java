package com.project.nmt.controller;

import com.project.nmt.model.OrderLog;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderLogRepository;
import com.project.nmt.repository.UserRepository;
import com.project.nmt.service.OrderLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class StockController {

    private final OrderLogService orderLogService;
    private final UserRepository userRepository;

    @GetMapping("/stock/transaction/{id}")
    public String getStockTransactionHistory(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                             @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate endDate,
                                             @PathVariable("id") Long id,
                                             Model model) {

        User user = userRepository.findById(id).get();
        List<OrderLog> list = orderLogService.getListByUserAndDate(user, startDate, endDate);

        model.addAttribute("orderLostList", list);

        return "stock-transaction";
    }

}
