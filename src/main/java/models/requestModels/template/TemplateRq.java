package models.requestModels.template;

import models.BaseRequestModel;

public class TemplateRq extends BaseRequestModel {
    private String name;
    private String surname;
    private Integer age;

    private TemplateRq(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        age = builder.age;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String surname;
        private Integer age;

        private Builder() {
        }

        public Builder setName(String val) {
            name = val;
            return this;
        }

        public Builder setSurname(String val) {
            surname = val;
            return this;
        }

        public Builder setAge(Integer val) {
            age = val;
            return this;
        }

        public TemplateRq build() {
            return new TemplateRq(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }
}
