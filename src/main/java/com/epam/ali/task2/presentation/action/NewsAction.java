package com.epam.ali.task2.presentation.action;

import com.epam.ali.task2.model.News;
import com.epam.ali.task2.presentation.form.NewsForm;
import com.epam.ali.task2.service.NewsService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsAction extends DispatchAction {

    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String VIEW = "view";
    private static final String EDIT = "edit";
    private static final String DELETE = "delete";
    private static final String SAVE = "save";
    private NewsService newsService = new NewsService();

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        newsForm.setNewsList(newsService.getNewsList());
        return mapping.findForward(LIST);
    }

    @Override
    protected ActionForward cancelled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(LIST);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        newsService.addNews(newsForm);
        return mapping.findForward(ADD);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        getNewsFromDb(form);
        return mapping.findForward(VIEW);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        getNewsFromDb(form);
        return mapping.findForward(EDIT);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        String[] deletedList = newsForm.getDeleted();
        if (deletedList != null) {
            for (int i = 0; i < deletedList.length; i++) {
                newsService.removeNews(Long.valueOf(deletedList[i]));
            }
            newsForm.setDeleted(null);
        } else {
            newsService.removeNews(newsForm.getNewsMessage().getId());
        }
        return mapping.findForward(DELETE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewsForm newsForm = (NewsForm) form;
        newsService.updateNews(newsForm);
        return mapping.findForward(SAVE);
    }

    private void getNewsFromDb(ActionForm form) {
        NewsForm newsForm = (NewsForm) form;
        News newsFromDb = newsService.getNews(Long.parseLong(newsForm.getId()));
        newsForm.setNewsMessage(newsFromDb);
    }

}
