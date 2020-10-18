package ru.mospolytech.dpo.domain.enumeration;

public enum CourseStudyLocation {
    BOLSHAYA_SEMENOVSKAYA("Ул. Большая Семеновская, 38 <br>(метро «Электрозаводская»)"), 
    ELECTROZAVODSKAYA("Ул. Автозаводская, 16 (метро «Автозаводская»)"), 
    PAVEL_KORCHAGIN("Ул. Павла Корчагина, 22 (метро «ВДНХ»)"), 
    DUBROVSKAYA("Ул. 1-я Дубровская, д. 16а (метро «Дубровка»)"), 
    AVIAMOTORNAYA("м. Авиамоторная ул. Лефортовский Вал, 26");
    
    private final String value;
    
    CourseStudyLocation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
