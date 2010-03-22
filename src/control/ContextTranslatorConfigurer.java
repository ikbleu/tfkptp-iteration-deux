package src.control;

import src.control.interfaces.ConfigurableTranslator;

/**
 * initialize the translator with the translations.
 * @author kagioglu
 */
class ContextTranslatorConfigurer {
    static void configure(ConfigurableTranslator ct) {
        ct.register("Groups", "HUD");
        ct.register("StructureOverview", "SO");
        ct.register("UnitOverview", "UO");
        ct.register("TechTree", "TT");
    }
}
