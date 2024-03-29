    package ru.springcourse.FirstRestApp.dto;

    import javax.persistence.Column;
    import javax.validation.constraints.NotEmpty;
    import javax.validation.constraints.Size;

    public class SensorDTO {
        @NotEmpty(message = "Name should not be empty")
        @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
        private String name;

        public SensorDTO() {
        }

        public SensorDTO(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
