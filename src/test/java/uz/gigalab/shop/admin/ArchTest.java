package uz.gigalab.shop.admin;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("uz.gigalab.shop.admin");

        noClasses()
            .that()
            .resideInAnyPackage("uz.gigalab.shop.admin.service..")
            .or()
            .resideInAnyPackage("uz.gigalab.shop.admin.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..uz.gigalab.shop.admin.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
