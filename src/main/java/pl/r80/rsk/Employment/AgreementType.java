package pl.r80.rsk.Employment;

import lombok.Getter;

@Getter
public enum AgreementType {

    UP("Umowa o pracę"),
    UZ("Umowa zlecenie"),
    UD("Umowa o dzieło");

    private String description;

    AgreementType(String agreementDescription) {
        this.description = agreementDescription;
    }
}
