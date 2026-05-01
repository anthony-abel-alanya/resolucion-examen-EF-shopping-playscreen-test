package edu.pe.cibertec.shooping.runners;

import io.cucumber.junit.platform.engine.Constants;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = Constants.GLUE_PROPERTY_NAME,
        value = "edu.pe.cibertec.shooping.steps,edu.pe.cibertec.shooping.hooks"
)
@ExtendWith(SerenityJUnit5Extension.class)
public class CucumberTestSuite {
}
