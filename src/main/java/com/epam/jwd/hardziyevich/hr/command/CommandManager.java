package com.epam.jwd.hardziyevich.hr.command;

import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowCreateVacancyPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowEditVacancyPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowForbiddenPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowLoginPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowNotFoundPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowProfilePageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowRecruiterPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowResultVacancySearchPage;
import com.epam.jwd.hardziyevich.hr.command.page.ShowSignUpPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowUserProfilePageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowUsersResponsesPage;
import com.epam.jwd.hardziyevich.hr.command.page.ShowVacancyPageCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.ApplyVacancyCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.AppointRecruiterCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.BanRecruiterCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.BanUserCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.CreateVacancyCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.DeleteUserCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.DeleteVacancyCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.EditProfileCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.LoginCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.LogoutCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.SignUpCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.UnbanUserCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.UpdateVacancyCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.UploadAvatarCommand;

public enum CommandManager {
    LOGIN(LoginCommand.getInstance()),
    LOGOUT(LogoutCommand.getInstance()),
    SIGNUP(SignUpCommand.getInstance()),
    EDIT_PROFILE(EditProfileCommand.getInstance()),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance()),
    SHOW_SIGNUP_PAGE(ShowSignUpPageCommand.getInstance()),
    DEFAULT(ShowMainPageCommand.getInstance()),
    SHOW_VACANCY_PAGE(ShowVacancyPageCommand.getInstance()),
    SHOW_NOT_FOUND_PAGE(ShowNotFoundPageCommand.getInstance()),
    SHOW_PROFILE_PAGE(ShowProfilePageCommand.getInstance()),
    SHOW_ADMIN_PAGE(ShowAdminPageCommand.getInstance()),
    APPOINT_RECRUITER(AppointRecruiterCommand.getInstance()),
    BAN_RECRUITER(BanRecruiterCommand.getInstance()),
    BAN_USER(BanUserCommand.getInstance()),
    UNBAN_USER(UnbanUserCommand.getInstance()),
    SHOW_EDIT_VACANCY_PAGE(ShowEditVacancyPageCommand.getInstance()),
    UPDATE_VACANCY(UpdateVacancyCommand.getInstance()),
    SHOW_RECRUITER_PAGE(ShowRecruiterPageCommand.getInstance()),
    DELETE_VACANCY(DeleteVacancyCommand.getInstance()),
    SHOW_CREATE_VACANCY_PAGE(ShowCreateVacancyPageCommand.getInstance()),
    CREATE_VACANCY(CreateVacancyCommand.getInstance()),
    SHOW_FORBIDDEN_PAGE(ShowForbiddenPageCommand.getInstance()),
    APPLY_VACANCY(ApplyVacancyCommand.getInstance()),
    SHOW_USERS_RESPONSES_PAGE(ShowUsersResponsesPage.getInstance()),
    SHOW_USER_PROFILE_PAGE(ShowUserProfilePageCommand.getInstance()),
    UPLOAD_AVATAR(UploadAvatarCommand.getInstance()),
    SHOW_RESULT_VACANCY_SEARCH_PAGE(ShowResultVacancySearchPage.getInstance()),
    DELETE_USER(DeleteUserCommand.getInstance());


    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command of(String name) {
        for (CommandManager action : values()) {
            if (action.name().equalsIgnoreCase(name)) {
                return action.command;
            }
        }
        return DEFAULT.command;
    }

}
