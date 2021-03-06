package SQLDatabase;

import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.DeleteQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.UpdateQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbJoin;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

/**
 * @author Noam Yefet
 */
class SQLQueryGenerator {

	/**
	 * Generate string of select query.
	 * 
	 * @param tabel
	 *            The table to select
	 * @param cs
	 *            Set of conditions
	 * @return string of select query.
	 */
	public static String generateSelectQuery1Table(DbTable tabel, Condition... cs) {

		return generateSelectOrderByQuery1Table(tabel, null, cs);

	}

	/**
	 * Generate string of select query.
	 * 
	 * @param tabel
	 *            The table to select
	 * @param orderByCol
	 *            If this parameter is not null - order results by this column
	 * @param cs
	 *            Set of conditions
	 * @return string of select query.
	 */
	public static String generateSelectOrderByQuery1Table(DbTable tabel, DbColumn orderByCol, Condition... cs) {
		SelectQuery resultQuery = new SelectQuery().addAllTableColumns(tabel);

		if (orderByCol != null)
			resultQuery.addOrderings(orderByCol);

		for (int ¢ = 0; ¢ < cs.length; ++¢)
			resultQuery.addCondition(cs[¢]);

		return resultQuery.validate() + "";

	}

	/**
	 * 
	 * Generate string of select query table join with other table
	 * 
	 * @param tabel
	 *            The table to select
	 * @param joinWithTable
	 *            The table to join with.
	 * @param joinByCol
	 *            join by this column
	 * @param orderByCol
	 *            order results by this column
	 * @param cs
	 *            Set of conditions
	 * @return string of select join query.
	 */
	public static String generateSelectLeftJoinWithQuery2Tables(DbTable tabel, DbTable joinWithTable,
			DbColumn joinByCol, DbColumn orderByCol, Condition... cs) {

		DbJoin custJoin = SQLDatabaseEntities.spec.addJoin(null, tabel.getAbsoluteName(), null,
				joinWithTable.getAbsoluteName(), joinByCol.getColumnNameSQL());

		SelectQuery resultQuery = new SelectQuery().addAllTableColumns(tabel).addAllTableColumns(joinWithTable)
				.addJoins(SelectQuery.JoinType.LEFT_OUTER, custJoin).addOrderings(orderByCol);

		for (int ¢ = 0; ¢ < cs.length; ++¢)
			resultQuery.addCondition(cs[¢]);

		return resultQuery.validate() + "";

	}

	/**
	 * Generate object of update query.
	 * 
	 * @param tabel
	 *            The table to select
	 * @param cs
	 *            Set of conditions
	 * @return string of select query.
	 */
	public static UpdateQuery generateUpdateQuery(DbTable tabel, Condition... cs) {
		UpdateQuery $ = new UpdateQuery(tabel);

		for (int ¢ = 0; ¢ < cs.length; ++¢)
			$.addCondition(cs[¢]);

		return $;

	}

	/**
	 * Generate string of delete query
	 * 
	 * @param tabel
	 *            The table to select
	 * @param cs
	 *            Set of conditions
	 * @return string of select query.
	 */
	public static String generateDeleteQuery(DbTable tabel, Condition... cs) {
		DeleteQuery $ = new DeleteQuery(tabel);

		for (int ¢ = 0; ¢ < cs.length; ++¢)
			$.addCondition(cs[¢]);

		return $.validate() + "";

	}

}
