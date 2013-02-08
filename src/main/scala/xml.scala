import xml.XML

/**
 * Реальный код из горячо мною любимого шлюза
 * StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<response performedDatetime=\"");
        sb.append(DateToXsdDatetimeFormatter.getInstance().timestampToString(new Date()));
        sb.append("\">\n<result code=\"");
        sb.append(code);
        sb.append("\" action=\"");
        sb.append(action);
        sb.append("\" shopId=\"");
        sb.append(shopId);
        sb.append("\" invoiceId=\"");
        sb.append(invoiceId);
        sb.append("\"/>\n</response>");
 */

val performedDatetime = "performedDatetime"
val response =
  <response performedDatetime={ performedDatetime }>
    <result code="code" action="action" shopId="shopId" invoiceId="invoiceId"/>
  </response>







val w = new java.io.StringWriter
XML.write(w, response, "UTF-8", true, null)

println(w)