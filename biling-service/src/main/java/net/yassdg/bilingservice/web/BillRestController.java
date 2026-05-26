package net.yassdg.bilingservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.services.BillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class BillRestController {

    private final BillService billService;

    @GetMapping("/bills")
    public ResponseEntity<Page<Bill>> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            Pageable page
    ) {
        log.info("enter find all with parameters : page = {}", page);
        return ResponseEntity.ok(billService.findAll(page));
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<Bill> findById(@PathVariable Long id) {
        log.info("enter find by id with parameters : id = {}", id);
        return ResponseEntity.ok(billService.findById(id));
    }
}
