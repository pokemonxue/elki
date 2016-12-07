package de.lmu.ifi.dbs.elki.algorithm.itemsetmining;
/*
 * This file is part of ELKI:
 * Environment for Developing KDD-Applications Supported by Index-Structures
 * 
 * Copyright (C) 2016
 * ELKI Development Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.result.FrequentItemsetsResult;
import de.lmu.ifi.dbs.elki.utilities.ClassGenericsUtil;
import de.lmu.ifi.dbs.elki.utilities.optionhandling.parameterization.ListParameterization;

/**
 * Regression test for APRIORI.
 *
 * @author Erich Schubert
 */
public class APRIORITest extends AbstractFrequentItemsetAlgorithmTest {
  @Test
  public void testMissing() {
    Database db = loadTransactions(UNITTEST + "itemsets/missing1.txt", 4);
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 1);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 14, res.getItemsets().size());
      for(Itemset i : res.getItemsets()) {
        assertEquals("Bad support", 4 - i.length(), i.getSupport());
      }
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 0.5);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 10, res.getItemsets().size());
      for(Itemset i : res.getItemsets()) {
        assertEquals("Bad support", 4 - i.length(), i.getSupport());
      }
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 3);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 4, res.getItemsets().size());
      for(Itemset i : res.getItemsets()) {
        assertEquals("Bad support", 4 - i.length(), i.getSupport());
      }
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 4);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 0, res.getItemsets().size());
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 1);
      params.addParameter(APRIORI.Parameterizer.MINLENGTH_ID, 2);
      params.addParameter(APRIORI.Parameterizer.MAXLENGTH_ID, 3);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 10, res.getItemsets().size());
      for(Itemset i : res.getItemsets()) {
        assertEquals("Bad support", 4 - i.length(), i.getSupport());
      }
    }
  }

  @Test
  public void testIncreasing() {
    Database db = loadTransactions(UNITTEST + "itemsets/increasing.txt", 4);
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 1);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 15, res.getItemsets().size());
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, .5);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 7, res.getItemsets().size());
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 3);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 3, res.getItemsets().size());
    }
    {
      ListParameterization params = new ListParameterization();
      params.addParameter(APRIORI.Parameterizer.MINSUPP_ID, 4);
      APRIORI ap = ClassGenericsUtil.parameterizeOrAbort(APRIORI.class, params);
      FrequentItemsetsResult res = ap.run(db);
      assertEquals("Size not as expected.", 1, res.getItemsets().size());
    }
  }
}
