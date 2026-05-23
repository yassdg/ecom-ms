package net.yassdg.bilingservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.exception.BillNotFoundException;
import net.yassdg.bilingservice.repository.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BillService {

    public static final String NOT_FOUND_ERROR_MESSAGE = "Can not find bill with id %s";

    private final BillRepository billRepository;

    public Page<Bill> findAll(Pageable pageable) {
        log.info("enter find all with parameters : pageable ={}", pageable);
        return billRepository.findAll(pageable);
    }

    public Bill findById(Long id) {
        log.info("enter find by id with parameters : id ={}", id);
        return billRepository
                .findById(id)
                .orElseThrow(() -> new BillNotFoundException(NOT_FOUND_ERROR_MESSAGE.formatted(id)));
    }

}
