SELECT 
Folio AS TAL_ID,  
Cliente AS CLIENTE_ID, 
Proveedor AS PROVEEDOR_ID, 
recoge AS TAL_RECOGE, 
entrega AS TAL_ENTREGA, 
'' AS TAL_FRACCION, 
'' AS TAL_CLASE, 
'' AS TAL_CUOTA, 
'' AS TAL_VALOR, 
'' AS TAL_BUL_NUM1, 
'' AS TAL_BUL_NUM2, 
'' AS TAL_BUL_NUM3, 
'' AS TAL_BUL_NUM4, 
'' AS TAL_BUL_CLASE1, 
'' AS TAL_BUL_CLASE2, 
'' AS TAL_BUL_CLASE3, 
'' AS TAL_BUL_CLASE4, 
Producto AS TAL_PRODUCTO1, 
descripcion2 AS TAL_PRODUCTO2, 
descripcion3 AS TAL_PRODUCTO3, 
descripcion4 AS TAL_PRODUCTO4, 
peso1 AS TAL_PESO1, 
peso2 AS TAL_PESO2, 
peso3 AS TAL_PESO3, 
peso4 AS TAL_PESO4, 
'' AS TAL_VOL_MTS1, 
'' AS TAL_VOL_MTS2, 
'' AS TAL_VOL_MTS3, 
'' AS TAL_VOL_MTS4, 
pesot AS TAL_VOL_PESO, 
Operador AS TAL_OPERADOR, 
Unidad AS TAL_UNIDAD, 
Placas AS TAL_PLACAS, 
'' AS TAL_REEMBARCARSE, 
'' AS TAL_CONDU_DE, 
'' AS TAL_CONDU_A, 
'' AS TAL_OBSER, 
Flete AS TAL_FLETE, 
Seguro AS TAL_SEGURO, 
Linea AS TAL_OLINEAS, 
Recoleccion AS TAL_RECOLEC, 
EntregaD AS TAL_E_DOM, 
Maniobras AS TAL_MANIOBRAS, 
Importe AS TAL_IMPORTE, 
IVA as TAL_IVA, 
Subtotal AS TAL_SUBTOTAL, 
Retenido AS TAL_RETENIDO, 
Total AS TAL_TOTAL, 
Letra AS TAL_LETRA, 
Fecha AS TAL_FECHA, 
Status AS TAL_STATUS 
FROM guias 
ORDER BY TAL_ID ASC