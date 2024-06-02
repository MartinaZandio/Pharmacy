<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>Pharmacy DataBase</title>
	<meta http-equiv="Content-Language" content="English" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
	</head>
	<body>
	<div id="wrap">
	
	<div id="top"></div>
	
	<div id="content">
	
	<div class="header">
	<h1><a href="#">Blue Freedom</a></h1>
	<h2>Free xhtml/css Template</h2>
	</div>
	
	<div class="breadcrumbs">
	<a href="#">Home</a> &middot; You are here
	</div>
	
	<div class="middle">
				
	<h2>Template dummy text</h2>
	   <p><b><xsl:value-of select="//name" /></b></p>
	   <p><b>Contents: </b><xsl:value-of select="//content" /></p>
	   <p><b>Medicines:</b></p>
	   <table border="1">
	      <th>Medicine</th>
	      <th>Amount</th>
	      <xsl:for-each select="Pharmacy/Stock/Medicine">
	      <xsl:sort select="@name" />
	         <xsl:if test="salary &gt; 0">
	            <tr>
	            <td><i><xsl:value-of select="@name" /></i></td>
	            <td><xsl:value-of select="amount" /></td>
	            </tr>
	         </xsl:if>
	      </xsl:for-each>
	   </table>
	   <br /><br />
	<img src="images/minimal.jpg" alt="Preview of Minimalistic Template" />
	 		
	</div>
			
	<div class="right">
			
	<h2>Navigation</h2>
	
	<ul>
	<li><a href="http://www.minimalistic-design.net">Minimalistic Design</a></li>
	<li><a href="http://www.oswd.org">Open Source Web Design</a></li>
	<li><a href="http://www.opendesigns.org">Open Designs</a></li>
	<li><a href="http://www.openwebdesign.org">Open Web Design</a></li>
	<li><a href="http://www.iollo.com">Iollo's review highway</a></li>
	<li><a href="http://www.historyexplorer.net">History Timelines</a></li>
	<li><a href="http://www.quakerparrot.info">Quaker Parrot</a></li>
	<li><a href="http://www.moneybookersclub.com">Moneybookers Club</a></li>
	</ul>
			
	</div>
	
	<div id="clear"></div>
	
	</div>
	
	<div id="bottom"></div>
	
	</div>
	
	<div id="footer">
	Design by <a href="http://www.minimalistic-design.net">Minimalistic Design</a>
	</div>
	
	</body>
	</html>
	
</xsl:template>

</xsl:stylesheet>