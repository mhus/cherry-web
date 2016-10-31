package de.mhus.cherry.renderer.jsp.tagext;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import de.mhus.lib.cao.CaoNode;

public class ChildrenTagHandler extends TagSupport {

	private static final long serialVersionUID = 1L;
	private CaoNode res;
	private Collection<CaoNode> nodes;
	private String iteratorName;
	private Iterator<CaoNode> iterator;

	public void setResource(CaoNode res) {
		this.res = res;
	}

	public void setIterator(String iteratorName) {
		this.iteratorName = iteratorName;
	}
	
	@Override
	public int doStartTag() throws JspException {
		nodes = res.getNodes();
		iterator = nodes.iterator();

	    if(iterate()) {
	    	return EVAL_BODY_INCLUDE;
	    }
	    return SKIP_BODY;
	}
	
	private boolean iterate() throws JspException {
		if (!iterator.hasNext()) return false;
	    try{
	    	pageContext.setAttribute(iteratorName, iterator.next() );
	    } catch(Exception e){
	      throw new JspException(e.toString());
	    }
	    return true;
	}

	@Override
	public int doAfterBody() throws JspException {
		
	    if(iterate()) {
	    	return EVAL_BODY_AGAIN;
	    }
	    return SKIP_BODY;
	  }
	
}
