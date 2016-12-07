package de.lmu.ifi.dbs.elki.algorithm.itemsetmining;

/*
 * This file is part of ELKI:
 * Environment for Developing KDD-Applications Supported by Index-Structures
 * 
 * Copyright (C) 2016
 * Ludwig-Maximilians-Universität München
 * Lehr- und Forschungseinheit für Datenbanksysteme
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

import de.lmu.ifi.dbs.elki.data.BitVector;
import de.lmu.ifi.dbs.elki.data.SparseNumberVector;
import de.lmu.ifi.dbs.elki.data.type.VectorFieldTypeInformation;

/**
 * APRIORI itemset.
 * 
 * @author Erich Schubert
 * @since 0.7.0
 */
public class OneItemset extends Itemset {
  /**
   * Trivial item.
   */
  int item;

  /**
   * Constructor of 1-itemset.
   * 
   * @param item Item
   */
  public OneItemset(int item) {
    this.item = item;
  }

  /**
   * Constructor with initial support.
   * 
   * @param item Item
   * @param support Support
   */
  public OneItemset(int item, int support) {
    this.item = item;
    this.support = support;
  }

  @Override
  public int length() {
    return 1;
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean containedIn(SparseNumberVector bv) {
    // Ignore deprecated, as we want binary search here.
    return bv.doubleValue(item) != 0.;
  }

  @Override
  public
  int iter() {
    return 0;
  }

  @Override
  public
  boolean iterValid(int iter) {
    return iter == 0;
  }

  @Override
  public
  int iterAdvance(int iter) {
    return 1;
  }

  @Override
  public
  int iterDim(int iter) {
    assert (iter == 0);
    return item;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof OneItemset) {
      return item == ((OneItemset) obj).item;
    }
    return super.equals(obj);
  }

  @Override
  public int compareTo(Itemset o) {
    if(o instanceof OneItemset) {
      int oitem = ((OneItemset) o).item;
      return item < oitem ? -1 : item > oitem ? +1 : 0;
    }
    return super.compareTo(o);
  }

  @Override
  public StringBuilder appendTo(StringBuilder buf, VectorFieldTypeInformation<BitVector> meta) {
    String lbl = (meta != null) ? meta.getLabel(item) : null;
    if(lbl == null) {
      buf.append(item);
    }
    else {
      buf.append(lbl);
    }
    return buf.append(": ").append(support);
  }
}
