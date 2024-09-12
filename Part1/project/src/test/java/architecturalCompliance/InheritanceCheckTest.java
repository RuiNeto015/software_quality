package architecturalCompliance;

import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.finance.project", importOptions = ImportOption.DoNotIncludeTests.class)
public class InheritanceCheckTest {

    @ArchTest
    static void checkInheritanceOfEntitiesInterface(JavaClasses classes) {
        classes().that().implement(Entity.class)
                .should().haveSimpleNameEndingWith("Entity")
                .check(classes);
    }

    @ArchTest
    static void checkInheritanceOfValueObjectInterface(JavaClasses classes) {
        classes().that().implement(ValueObject.class)
                .should().haveSimpleNameEndingWith("ValueObject")
                .check(classes);
    }

    @ArchTest
    static void checkInheritanceOfPersonPersistenceInterface(JavaClasses classes) {
        classes().that().implement(IPersonRepository.class)
                .should().haveSimpleNameEndingWith("PersonRepository")
                .check(classes);
    }

    @ArchTest
    static void checkInheritanceOfCategoryPersistenceInterface(JavaClasses classes) {
        classes().that().implement(ICategoryRepository.class)
                .should().haveSimpleNameEndingWith("CategoryRepository")
                .check(classes);
    }

}
