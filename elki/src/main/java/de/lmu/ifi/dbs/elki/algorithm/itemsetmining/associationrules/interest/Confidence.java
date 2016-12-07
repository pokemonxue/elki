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

/**
 * Confidence interestingness measure, support(X \cup Y)/support(X).
 * 
 * Reference:
 * <p>
 * R. Agrawal, T. Imielinski, and A. Swami
 * Mining association rules between sets of items in large databases<br />
 * Proc. ACM SIGMOD International Conference on Management of Data
 * </p>
 * 
 * @author Frederic Sautter
 */
@Reference(authors = "R. Agrawal, T. Imielinski, and A. Swami", //
    title = "Mining association rules between sets of items in large databases", //
    booktitle = "Proc. ACM SIGMOD International Conference on Management of Data", //
    url = "https://doi.org/10.1145/170036.170072")
public class Confidence implements InterestingnessMeasure {
  /**
   * Constructor.
   */
  public Confidence() {
    super();
  }

  @Override
  public double measure(int t, int sX, int sY, int sXY) {
    return sXY / (double) sX;
  }
}
