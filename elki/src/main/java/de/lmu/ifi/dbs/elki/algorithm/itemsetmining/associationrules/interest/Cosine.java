package de.lmu.ifi.dbs.elki.algorithm.itemsetmining.associationrules.interest;

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

import de.lmu.ifi.dbs.elki.utilities.documentation.Reference;

import net.jafama.FastMath;

/**
 * Cosine interestingness measure, P(A,B)/sqrt(P(A)*P(B)).
 * 
 * The interestingness measure called IS by Tan and Kumar.
 * 
 * Reference:
 * <p>
 * P. Tan and V. Kumar<br />
 * Interestingness measures for association patterns: A perspective<br />
 * In Proc. Workshop on Postprocessing in Machine Learning and Data Mining
 * </p>
 * 
 * @author Frederic Sautter
 */
@Reference(authors = "P. Tan and V. Kumar", //
    title = "Interestingness measures for association patterns: A perspective", //
    booktitle = "Proc. Workshop on Postprocessing in Machine Learning and Data Mining", //
    url="https://www.cs.umn.edu/sites/cs.umn.edu/files/tech_reports/00-036.pdf")
public class Cosine implements InterestingnessMeasure {
  /**
   * Constructor.
   */
  public Cosine() {
    super();
  }

  @Override
  public double measure(int t, int sX, int sY, int sXY) {
    return sXY / FastMath.sqrt(sX * sY);
  }
}
