package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.VacancyDto;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShowResultVacancySearchPage implements Command {
    public static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/resultVacancySearch.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static ShowResultVacancySearchPage instance = null;

    private ShowResultVacancySearchPage(){

    }

    public static ShowResultVacancySearchPage getInstance(){
        if(instance == null){
            instance = new ShowResultVacancySearchPage();
        }
        return instance;
    }

    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String searchVacancy = requestContext.getParameter("vacancySearch").trim().toLowerCase();
        final Optional<List<VacancyDto>> allRelevantVacancies = vacancyService.findAllRelevantVacancies();
        List<VacancyDto> resultSearch = new ArrayList<>();
        if(allRelevantVacancies.isPresent()){
            final List<VacancyDto> vacancyDtos = allRelevantVacancies.get();
            resultSearch = vacancyDtos.stream().filter(vacancyDto -> vacancyDto.getVacancyName().toLowerCase().equalsIgnoreCase(searchVacancy) ||
                    vacancyDto.getCompanyName().toLowerCase().equalsIgnoreCase(searchVacancy) ||
                    vacancyDto.getSkillsDescription().toLowerCase().contains(searchVacancy) ||
                    vacancyDto.getDescription().toLowerCase().contains(searchVacancy) ||
                    vacancyDto.getVacancyName().toLowerCase().contains(searchVacancy) ||
                    vacancyDto.getCompanyName().toLowerCase().contains(searchVacancy)).collect(Collectors.toList());
        }
        requestContext.setAttribute("searchResult", resultSearch);
        return RESPONSE_CONTEXT;
    }
}
