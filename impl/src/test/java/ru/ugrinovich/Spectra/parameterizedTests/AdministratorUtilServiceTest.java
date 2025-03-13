package ru.ugrinovich.Spectra.parameterizedTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.ugrinovich.Spectra.entities.Administrator;
import ru.ugrinovich.Spectra.services.administrator.AdministratorUtil;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AdministratorUtilServiceTest {


    private static Stream<Arguments> provideAdministratorTestArguments(){
        return Stream.of(Arguments.of(Administrator.builder().name("barsik").build(), Administrator.builder().name("kotik").build()),
                Arguments.of(Administrator.builder().name(null).build(), Administrator.builder().name("kotik").build()),
                Arguments.of(Administrator.builder().name("barsik").build(), Administrator.builder().name("").build()));
    }

    @ParameterizedTest
    @MethodSource("provideAdministratorTestArguments")
    void updateAdministrator_WhenNotNull(Administrator foundAdministrator, Administrator updatedAdministrator){
        AdministratorUtil.updateDataAdmin(foundAdministrator, updatedAdministrator);
        assertEquals(foundAdministrator.getName(), updatedAdministrator.getName());
    }
}
