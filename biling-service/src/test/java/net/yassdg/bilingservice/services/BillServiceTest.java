package net.yassdg.bilingservice.services;

import net.yassdg.bilingservice.data.BillTestData;
import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.exception.BillNotFoundException;
import net.yassdg.bilingservice.repository.BillRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static net.yassdg.bilingservice.services.BillService.NOT_FOUND_ERROR_MESSAGE;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.anyLong;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    private Page<Bill> expectedPageOfBills;
    private Page<Bill> resultPageOfBills;
    private Bill expectedBill;
    private Bill resultBill;

    @BeforeEach
    void init() {
        expectedPageOfBills = null;
        resultPageOfBills = null;
        expectedBill = null;
        resultBill = null;
    }

    @Test
    void shouldReturnPagedBills() {
        expectedPageOfBills = BillTestData.pageOfBills();
        when(billRepository.findAll(BillTestData.PAGE)).thenReturn(expectedPageOfBills);

        resultPageOfBills = billService.findAll(BillTestData.PAGE);

        assertThat(resultPageOfBills).isNotNull();
        assertThat(resultPageOfBills.getContent())
                .hasSameSizeAs(expectedPageOfBills.getContent())
                .containsExactlyInAnyOrderElementsOf(expectedPageOfBills.getContent());
        verify(billRepository).findAll(BillTestData.PAGE);
        verifyNoMoreInteractions(billRepository);
    }

    @Test
    void shouldReturnBillWhenBillExists() {
         expectedBill = BillTestData.billOne();
        when(billRepository.findById(BillTestData.ID)).thenReturn(Optional.of(expectedBill));

        resultBill = billService.findById(BillTestData.ID);

        assertThat(resultBill)
                .isNotNull()
                .usingRecursiveAssertion()
                .isEqualTo(expectedBill);
        verify(billRepository).findById(BillTestData.ID);
        verifyNoMoreInteractions(billRepository);
    }

    @Test
    void shouldThrowExceptionWhenBillDoesNotExist() {
        when(billRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> billService.findById(BillTestData.ID))
                .isInstanceOf(BillNotFoundException.class)
                .hasMessage(NOT_FOUND_ERROR_MESSAGE.formatted(BillTestData.ID));
        verify(billRepository).findById(BillTestData.ID);
        verifyNoMoreInteractions(billRepository);
    }
}