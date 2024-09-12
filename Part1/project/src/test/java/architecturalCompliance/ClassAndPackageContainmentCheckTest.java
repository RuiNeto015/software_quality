package architecturalCompliance;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.junit.jupiter.api.DisplayName;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.finance.project", importOptions = ImportOption.DoNotIncludeTests.class)
public class ClassAndPackageContainmentCheckTest {

    @ArchTest
    @DisplayName("Check for services and package containment")
    static void checkServicesClassAndPackageContainment(JavaClasses classes) {
        classes().that().resideInAPackage("..*Services")
                .should().haveSimpleNameEndingWith("Service")
                .check(classes);
    }

    @ArchTest
    static void checkRepositoryClassAndPackageContainment(JavaClasses classes) {
        classes().that().resideInAPackage("..*Controllers")
                .should().haveSimpleNameEndingWith("Controller")
                .check(classes);
    }

    @ArchTest
    static void checkExceptionsClassAndPackageContainment(JavaClasses classes) {
        classes().that().resideInAPackage("..exceptions")
                .should().haveSimpleNameEndingWith("Exception")
                .check(classes);
    }

}
