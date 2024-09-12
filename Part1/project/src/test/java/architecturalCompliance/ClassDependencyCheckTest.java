package architecturalCompliance;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.finance.project", importOptions = ImportOption.DoNotIncludeTests.class)
public class ClassDependencyCheckTest {
    @ArchTest
    static void checkControllersClassDependencyTest(JavaClasses classes) {
        classes().that().haveNameMatching(".*ControllerREST")
                .should().onlyHaveDependentClassesThat()
                .haveSimpleName(".*Service")
                .orShould().haveSimpleName(".*DTO")
                .check(classes);
    }
}
