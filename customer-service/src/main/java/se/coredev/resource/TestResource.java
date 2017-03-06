package se.coredev.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("test")
public final class TestResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAsPlainText() {
		return "Hello!";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAsHtml() {
		return "<b>Hello!</b>";
	}
	
	@GET
	@Path("parameter")
	//.../test/parameter?value=hello
	public String echoParameter(@BeanParam PageRequestBean request){
		return String.format("size:%s, page:%s, sort:%s", request.getSize(), request.getPage(), request.getSort());
	}
//	@GET
//	@Path("parameter")
//	//.../test/parameter?value=hello
//	public String echoParameter(@QueryParam("size") @DefaultValue("10") int size,
//								@QueryParam("page") @DefaultValue("0") int page,
//								@QueryParam("sort") @DefaultValue("asc") String sort){
//		return String.format("size:%s, page:%s, sort:%s", size, page, sort);
//	}
	
	public static class PageRequestBean {
		
		@QueryParam("size") @DefaultValue("10") private int size;
		@QueryParam("page") @DefaultValue("0") private int page;
		@QueryParam("sort") @DefaultValue("asc") private String sort;
		
		public int getSize() {
			return size;
		}
		public int getPage() {
			return page;
		}
		public String getSort() {
			return sort;
		}
	}
}
