package com.glucoclock.views;

import com.glucoclock.views.patient.HistoryView;
import com.glucoclock.views.patient.DownloadPage;
import com.glucoclock.views.patient.LogbookView;
import com.glucoclock.views.researcher.*;
import com.glucoclock.views.doctor.DoctorSetting1;
import com.glucoclock.views.doctor.DoctorSetting2;
import com.glucoclock.views.doctor.DoctorSignUp1;
import com.glucoclock.views.doctor.DoctorSignUp2;
import com.glucoclock.views.patient.*;
import com.glucoclock.views.researcher.ResearcherSetting1;
import com.glucoclock.views.researcher.ResearcherSetting2;
import com.glucoclock.views.researcher.ResearcherSignUp;
import com.glucoclock.views.researcher.ResearcherSignUp2;
import com.glucoclock.views.patient.QuestionnaireView;
import com.glucoclock.views.templates.sugarfree.about.AboutView;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import java.util.ArrayList;
import java.util.List;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "Glucoclock", shortName = "Glucoclock", enableInstallPrompt = false)
@Theme(themeFolder = "glucoclock")
@PageTitle("Main")
public class MainLayout extends AppLayout {

    public static class MenuItemInfo {

        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("m-0", "text-l");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("Gluco'clock");
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("list-none", "m-0", "p-0");
        nav.add(list);

        for (RouterLink link : createLinks()) {
            ListItem item = new ListItem(link);
            list.add(item);
        }
        return nav;
    }

    private List<RouterLink> createLinks() {
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                new MenuItemInfo("Home", "la la-globe", HomeView.class), //

                new MenuItemInfo("About", "la la-file", AboutView.class), //

                new MenuItemInfo("SignUp", "la la-file", SignUp.class),

                new MenuItemInfo("Patient Start", "la la-file", PatientStart.class),

                new MenuItemInfo("Patient sign up page 1", "la la-user", PatientSignUp1.class),

                new MenuItemInfo("Patient sign up page 2", "la la-user", PatientSignUp2.class),

                new MenuItemInfo("Patient sign up page 3", "la la-user", PatientSignUp3.class),

                new MenuItemInfo("Patient Setting1", "la la-user", PatientSetting1.class),

                new MenuItemInfo("Patient Setting2", "la la-user", PatientSetting2.class),

                new MenuItemInfo("Doctor sign up", "la la-user", DoctorSignUp1.class),

                new MenuItemInfo("Doctor sign up 2", "la la-user", DoctorSignUp2.class),

                new MenuItemInfo("Doctor Setting1", "la la-user", DoctorSetting1.class),

                new MenuItemInfo("Doctor Setting2", "la la-user", DoctorSetting2.class),

                new MenuItemInfo("ResearcherSignUp1", "la la-user", ResearcherSignUp.class),

                new MenuItemInfo("ResearcherSignUp2", "la la-user", ResearcherSignUp2.class),

                new MenuItemInfo("Researcher Setting1", "la la-user", ResearcherSetting1.class),

                new MenuItemInfo("Researcher Setting2", "la la-user", ResearcherSetting2.class),

                new MenuItemInfo("Simple Logbook", "la la-file", SimpleLogBookView.class),

                new MenuItemInfo("Comprehensive Logbook", "la la-file", ComprehensiveLogBookView.class),

                new MenuItemInfo("Intensive Logbook", "la la-file", IntensiveLogBookView.class),

                //new MenuItemInfo("Questionnaire", "la la-file", QuestionnaireView.class),

                new MenuItemInfo("Download","", DownloadPage.class),

                new MenuItemInfo("View History","", HistoryView.class),

                new MenuItemInfo("LogBook","",LogbookView.class),

                new MenuItemInfo("Researcher Start","", ResearcherStart.class),

                new MenuItemInfo("Questionnaire","", QuestionnaireView.class),

                new MenuItemInfo("Comformation Page","",ConfirmationPage.class),
        };
        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());

        Span icon = new Span();
        icon.addClassNames("me-s", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s");

        link.add(icon, text);
        return link;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
