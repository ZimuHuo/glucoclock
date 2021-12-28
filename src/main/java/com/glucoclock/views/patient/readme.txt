HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            email.setAuthentication("glucoclock@gmail.com", "IloveJava");

            try {
                email.setFrom("glucoclock@gmail.com");
                email.addTo("theresa20130722@gmail.com","yokidoke607@gmail.com","jinjennifer0308@gmail.com","zg919@ic.ac.uk","Zimuhuo@outlook.com");
                email.setSubject("Alert");
                email.setHtmlMsg("Dude, what is wrong with your glucose level?");
            } catch (EmailException ex) {
                System.out.println("not working");
            }