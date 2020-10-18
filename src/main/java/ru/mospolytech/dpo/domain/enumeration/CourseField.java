package ru.mospolytech.dpo.domain.enumeration;

public enum CourseField {
    WORLDSKILLS_RUSSIA("WORLDSKILLS RUSSIA"), 
    CHILDREN_TECHNOPARK("Детский технопарк"), 
    INDUSTRY_AND_ECOLOGICAL_SAFETY("Техносферная безопасность"),
    TRANSPORT("Транспорт"), 
    WORK_WITH_SOFTWARE_PRODUCTS("Работа с программными продуктами"), 
    LABOR_PROTECTION_AND_ENERGY_EFFICIENCY("Охрана труда и энергоэффективность"), 
    TRANSPORT_OF_DANGEROUS_GOODS("Перевозка опасных грузов"), 
    BUILDING("Строительство"), 
    MECHANICAL_ENGINEERING("Машиностроение"), 
    ECONOMICS_AND_MANAGMENT("Экономика и управление"),
    EDUCATION_AND_PEDAGOGY("Образование и педагогика"), 
    LINGUISTICS("Лингвистика");
    
    private final String value;
    
    CourseField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}