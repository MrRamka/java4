package com.yabcompany.components;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ViewResolverErrorExtended extends AbstractTemplateViewResolver implements ViewResolver {

    private static final String ERROR_URL_PREFIX  = "error:";
    private Map<Integer, String> ERRORS_CODE_PAGES = new HashMap<>();

    protected String errorPrefix ="";
    protected String errorSuffix = "";

    public ViewResolverErrorExtended() {
        setViewClass(requiredViewClass());
    }

    public ViewResolverErrorExtended(String prefix, String suffix){
        this();
        setPrefix(prefix);
        setSuffix(suffix);
    }

    protected Class<?> requiredViewClass(){
        return AbstractTemplateView.class;
    }


    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        fillErrors();
        // If this resolver is not supposed to handle the given view,
        // return null to pass on to the next resolver in the chain.
        if (!canHandle(viewName, locale)) {
            return null;
        }
        // Check for special "error:" prefix
        if (viewName.startsWith(ERROR_URL_PREFIX)){
            String errorCode = viewName.substring((ERROR_URL_PREFIX.length())).trim();
            try{
                int integerErrorCode = Integer.parseInt(errorCode);
                if (ERRORS_CODE_PAGES.containsKey(integerErrorCode)){
                    InternalResourceView view = new InternalResourceView(ERRORS_CODE_PAGES.get(integerErrorCode));
                    return applyLifecycleMethods(ERRORS_CODE_PAGES.get(integerErrorCode), view);
                }
            }catch (NumberFormatException ex){
                return null;
            }
        }


        // Check for special "redirect:" prefix.
        if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
            String redirectUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
            RedirectView view = new RedirectView(redirectUrl,
                    isRedirectContextRelative(), isRedirectHttp10Compatible());
            String[] hosts = getRedirectHosts();
            if (hosts != null) {
                view.setHosts(hosts);
            }
            return applyLifecycleMethods(REDIRECT_URL_PREFIX, view);
        }

        // Check for special "forward:" prefix.
        if (viewName.startsWith(FORWARD_URL_PREFIX)) {
            String forwardUrl = viewName.substring(FORWARD_URL_PREFIX.length());
            InternalResourceView view = new InternalResourceView(forwardUrl);
            return applyLifecycleMethods(FORWARD_URL_PREFIX, view);
        }

//        ?? return new InternalResourceView(this.getPrefix() + viewName + this.getSuffix());
        return null;
    }



    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        InternalResourceView view = (InternalResourceView) super.buildView(viewName);
        return view;
    }

    private void fillErrors(){
        ERRORS_CODE_PAGES.put(404, this.errorPrefix + "err_404" + this.errorSuffix);
        ERRORS_CODE_PAGES.put(400, this.errorPrefix + "err_400" + this.errorSuffix);
    }

    public void setErrorPrefix(@Nullable String errorPrefix) {
        this.errorPrefix = (errorPrefix != null ? errorPrefix : "");
    }

    public void setErrorSuffix(@Nullable String errorSuffix) {
        this.errorSuffix = (errorSuffix != null ? errorSuffix: "");
    }

    public String getErrorPrefix() {
        return errorPrefix;
    }

    public String getErrorSuffix() {
        return errorSuffix;
    }

    public void addError(int errorCode, String errorPage){
        ERRORS_CODE_PAGES.put(errorCode, errorPage);
    }
}
