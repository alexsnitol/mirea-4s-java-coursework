package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.Vacancy;
import com.ineri.ineri_lk.repository.VacancyRepository;
import com.ineri.ineri_lk.util.HtmlTagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl extends AbstractServiceImpl<Vacancy, VacancyRepository> {

    @Autowired
    VacancyRepository vacancyRepository;

    @PostConstruct
    public void init() {
        this.defaultRepository = vacancyRepository;
    }

    public List<String> convertKeywordsToList(String keywords) {
        return Arrays.stream(keywords.split(";")).collect(Collectors.toList());
    }

    @Override
    public List<Vacancy> getAll() {
        List<Vacancy> vacancies = defaultRepository.findAll();

        for (Vacancy vacancy : vacancies) {
            configVacancy(vacancy);
        }

        return vacancies;
    }

    @Override
    public Vacancy getById(Long id) {
        return configVacancy(defaultRepository.findById(id).get());
    }

    private Vacancy configVacancy(Vacancy vacancy) {
        vacancy.setKeywordsList(convertKeywordsToList(vacancy.getKeywords()));
//        vacancy.setTextDescription      (HtmlTagConverter.convertLineBreakToTagBr(vacancy.getTextDescription()));
//        vacancy.setTextFeatures         (HtmlTagConverter.convertLineBreakToTagBr(vacancy.getTextFeatures()));
//        vacancy.setTextRequirements     (HtmlTagConverter.convertLineBreakToTagBr(vacancy.getTextRequirements()));
//        vacancy.setTextResponsibilities (HtmlTagConverter.convertLineBreakToTagBr(vacancy.getTextResponsibilities()));
//        vacancy.setTextStudy            (HtmlTagConverter.convertLineBreakToTagBr(vacancy.getTextStudy()));
//        vacancy.setTextSummary          (HtmlTagConverter.convertLineBreakToTagBr(vacancy.getTextSummary()));
        return vacancy;
    }

    public void setActiveStatus(Long id, boolean activeStatus) {
        Vacancy vacancy = getById(id);
        vacancy.setActive(activeStatus);
        save(vacancy);
    }

}
