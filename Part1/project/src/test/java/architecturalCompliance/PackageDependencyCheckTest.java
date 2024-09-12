package architecturalCompliance;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.finance.project", importOptions = ImportOption.DoNotIncludeTests.class)
public class PackageDependencyCheckTest {
    @ArchTest
    static void checkGroupControllersPackageDependencyTest(JavaClasses classes) {
        classes().that().resideInAPackage("..groupControllers..")
                .should().dependOnClassesThat().resideInAnyPackage("..applicationServices..", "..dtos..")
                .check(classes);
    }

    @ArchTest
    static void checkPersonControllersPackageDependencyTest(JavaClasses classes) {
        classes().that().resideInAPackage("..personControllers..")
                .should().dependOnClassesThat().resideInAnyPackage("..applicationServices..", "..dtos..")
                .check(classes);
    }

    @ArchTest
    static void checkOtherControllersPackageDependencyTest(JavaClasses classes) {
        classes().that().resideInAPackage("..otherControllers..")
                .should().dependOnClassesThat().resideInAnyPackage("..applicationServices..", "..dtos..")
                .check(classes);
    }
}
