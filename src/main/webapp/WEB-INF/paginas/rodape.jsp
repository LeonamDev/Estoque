<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<br/><br/>
<strong>Data da solicitação: </strong><fmt:formatDate value="${agora}"
                                     pattern="dd/MM/yyyy HH:mm:ss"/>