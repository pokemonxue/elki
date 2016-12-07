package de.lmu.ifi.dbs.elki.algorithm.clustering.hierarchical;

/*
 This file is part of ELKI:
 Environment for Developing KDD-Applications Supported by Index-Structures

 Copyright (C) 2016
 Ludwig-Maximilians-Universität München
 Lehr- und Forschungseinheit für Datenbanksysteme
 ELKI Development Team

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.junit.Test;

import de.lmu.ifi.dbs.elki.algorithm.AbstractSimpleAlgorithmTest;
import de.lmu.ifi.dbs.elki.algorithm.clustering.hierarchical.extraction.CutDendrogramByNumberOfClusters;
import de.lmu.ifi.dbs.elki.data.Clustering;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.result.Result;
import de.lmu.ifi.dbs.elki.utilities.ClassGenericsUtil;
import de.lmu.ifi.dbs.elki.utilities.optionhandling.parameterization.ListParameterization;
import de.lmu.ifi.dbs.elki.workflow.AlgorithmStep;

/**
 * Perform agglomerative hierarchical clustering, using the naive algorithm.
 *
 * @author Erich Schubert
 * @since 0.6.0
 */
public class MiniMaxTest extends AbstractSimpleAlgorithmTest {
  // TODO: add more data sets.

  /**
   * Run agglomerative hierarchical clustering with fixed parameters and compare
   * the result to a golden standard.
   */
  @Test
  public void testMiniMax() {
    Database db = makeSimpleDatabase(UNITTEST + "single-link-effect.ascii", 638);

    // Setup algorithm
    ListParameterization params = new ListParameterization();
    params.addParameter(CutDendrogramByNumberOfClusters.Parameterizer.MINCLUSTERS_ID, 3);
    params.addParameter(AlgorithmStep.Parameterizer.ALGORITHM_ID, MiniMax.class);
    CutDendrogramByNumberOfClusters c = ClassGenericsUtil.parameterizeOrAbort(CutDendrogramByNumberOfClusters.class, params);
    testParameterizationOk(params);

    // run clustering algorithm on database
    Result result = c.run(db);
    Clustering<?> clustering = findSingleClustering(result);
    testFMeasure(db, clustering, 0.938662648);
    testClusterSizes(clustering, new int[] { 200, 211, 227 });
  }

  /**
   * Run agglomerative hierarchical clustering with fixed parameters and compare
   * the result to a golden standard.
   */
  @Test
  public void testMiniMax2() {
    Database db = makeSimpleDatabase(UNITTEST + "3clusters-and-noise-2d.csv", 330);

    // Setup algorithm
    ListParameterization params = new ListParameterization();
    params.addParameter(CutDendrogramByNumberOfClusters.Parameterizer.MINCLUSTERS_ID, 3);
    params.addParameter(AlgorithmStep.Parameterizer.ALGORITHM_ID, MiniMax.class);
    CutDendrogramByNumberOfClusters c = ClassGenericsUtil.parameterizeOrAbort(CutDendrogramByNumberOfClusters.class, params);
    testParameterizationOk(params);

    // run clustering algorithm on database
    Result result = c.run(db);
    Clustering<?> clustering = findSingleClustering(result);
    testFMeasure(db, clustering, 0.914592130);
    testClusterSizes(clustering, new int[] { 59, 112, 159 });
  }
}
