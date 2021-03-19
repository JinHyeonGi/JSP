package mvc.control;

import mvc.action.Action;
import mvc.action.IndexAction;

// 요청에 맞는 액션을 리턴해주는 공장
public class ActionFactory {

	private static ActionFactory factory;
	public ActionFactory() {}
	
	public static synchronized ActionFactory getInstance() {
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("index")) {
			action = new IndexAction();
		}
		return action;
	}
}
