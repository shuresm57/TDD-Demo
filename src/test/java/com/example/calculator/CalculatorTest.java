package com.example.calculator;

import com.example.tdddemo.calculator.Calculator;
import com.example.tdddemo.calculator.NumberSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    @Mock
    private NumberSource numberSource;

    private Calculator cut;

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() {
        cut = new Calculator(numberSource);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_PositiveAndPositive_ReturnsPositive(long value) {
        Mockito.when(numberSource.next()).thenReturn( value, value);
        long result = cut.multiply();
        assertTrue( result > 0);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_PositiveAndNegative_ReturnsNegative(long value){
        Mockito.when(numberSource.next()).thenReturn( value, -value);
        long result = cut.multiply();
        assertTrue( result < 0);
    }
    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndPositive_ReturnsNegative(long value){
        Mockito.when(numberSource.next()).thenReturn( -value, value);
        long result = cut.multiply();
        assertTrue( result < 0);
    }
    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndNegative_ReturnsPositive(long value){
        Mockito.when(numberSource.next()).thenReturn( -value, -value);
        long result = cut.multiply();
        assertTrue( result > 0);
    }
}
