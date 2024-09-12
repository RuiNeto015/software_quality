package architecturalCompliance;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.properties.HasName.AndFullName.Predicates.fullNameMatching;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.finance.project", importOptions = ImportOption.DoNotIncludeTests.class)
public class DependencyCyclesAndLayeredArchitectureTest {

    @ArchTest
    static void applicationDoesNotHaveCyclicDependencies(JavaClasses classes) {
        slices().matching("com.finance.project.(*)..").should().beFreeOfCycles().check(classes);
    }

    @ArchTest
    static void applicationFollowsLayeredArchitecture(JavaClasses classes) {
        layeredArchitecture()
                .consideringAllDependencies()
                .ignoreDependency(fullNameMatching("com.finance.project.Bootstrapping"), alwaysTrue())
                .ignoreDependency(fullNameMatching("com.finance.project.ProjectApplication"), alwaysTrue())
                .layer("controllerLayer").definedBy("com.finance.project.controllerLayer..")
                .layer("applicationLayer").definedBy("com.finance.project.applicationLayer..")
                .layer("domainLayer").definedBy("com.finance.project.domainLayer..")
                .layer("dtos").definedBy("com.finance.project.dtos..")
                .layer("dataModel").definedBy("com.finance.project.dataModel..")
                .layer("infrastructureLayer").definedBy("com.finance.project.infrastructureLayer..")
                .layer("persistenceLayer").definedBy("com.finance.project.persistenceLayer..")

                .whereLayer("controllerLayer").mayNotBeAccessedByAnyLayer()
                .whereLayer("applicationLayer").mayOnlyBeAccessedByLayers("controllerLayer")
                .whereLayer("domainLayer").mayOnlyBeAccessedByLayers("applicationLayer", "dataModel", "infrastructureLayer")
                .whereLayer("dtos").mayOnlyBeAccessedByLayers("controllerLayer", "applicationLayer")
                .whereLayer("dataModel").mayOnlyBeAccessedByLayers("infrastructureLayer", "persistenceLayer")
                .whereLayer("infrastructureLayer").mayOnlyBeAccessedByLayers("applicationLayer")
                .whereLayer("persistenceLayer").mayOnlyBeAccessedByLayers("infrastructureLayer")

                .check(classes);
    }
}
