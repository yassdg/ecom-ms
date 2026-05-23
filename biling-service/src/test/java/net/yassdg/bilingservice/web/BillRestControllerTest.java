package net.yassdg.bilingservice.web;


import net.yassdg.bilingservice.data.BillTestData;
import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.exception.BillNotFoundException;
import net.yassdg.bilingservice.services.BillService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class BillRestControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillRestController billRestController;

    private Page<Bill> expectedPageOfBills;
    private ResponseEntity<Page<Bill>> resultPageOfBills;
    private Bill expectedBill;
    private ResponseEntity<Bill> resultBill;

    @BeforeEach
    void init() {
        expectedPageOfBills = null;
        resultPageOfBills = null;
        expectedBill = null;
        resultBill = null;
    }

    @Test
    void shouldReturnBillWhenExists() {
        expectedBill = BillTestData.billOne();
        when(billService.findById(anyLong())).thenReturn(expectedBill);

        resultBill= billRestController.findById(BillTestData.ID);

        assertThat(resultBill)
                .isNotNull();
        assertThat(resultBill.getStatusCode())
                .isNotNull()
                .isEqualTo(HttpStatusCode.valueOf(HttpStatus.OK.value()));
        assertThat(resultBill.getBody())
                .usingRecursiveAssertion()
                .isEqualTo(expectedBill);
        verify(billService).findById(BillTestData.ID);
        verifyNoMoreInteractions(billService);
    }

    @Test
    void shouldThrowExceptionWhenBillDoesNotExist() {
        when(billService.findById(anyLong())).thenThrow(new BillNotFoundException(BillTestData.ERROR_MESSAGE));

        assertThatThrownBy(() -> billService.findById(BillTestData.ID))
                .isInstanceOf(BillNotFoundException.class)
                .hasMessage(BillTestData.ERROR_MESSAGE);
        verify(billService).findById(BillTestData.ID);
        verifyNoMoreInteractions(billService);
    }

    @Test
    void shouldReturnPagedBills() {
        expectedPageOfBills = BillTestData.pageOfBills();
        when(billService.findAll(any())).thenReturn(expectedPageOfBills);

        resultPageOfBills= billRestController.findAll(BillTestData.PAGE);

        assertThat(resultPageOfBills)
                .isNotNull();
        assertThat(resultPageOfBills.getStatusCode())
                .isNotNull()
                .isEqualTo(HttpStatusCode.valueOf(HttpStatus.OK.value()));
        assertThat(resultPageOfBills.getBody())
                .isNotNull()
                .isEqualTo(expectedPageOfBills);
        verify(billService).findAll(BillTestData.PAGE);
        verifyNoMoreInteractions(billService);
    }
}