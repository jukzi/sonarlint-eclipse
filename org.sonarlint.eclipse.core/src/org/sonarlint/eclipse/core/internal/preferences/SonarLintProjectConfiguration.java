/*
 * SonarLint for Eclipse
 * Copyright (C) 2015-2023 SonarSource SA
 * sonarlint@sonarsource.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarlint.eclipse.core.internal.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.eclipse.jdt.annotation.Nullable;
import org.sonarlint.eclipse.core.internal.resources.ExclusionItem;
import org.sonarlint.eclipse.core.internal.resources.SonarLintProperty;
import org.sonarsource.sonarlint.core.serverconnection.ProjectBinding;

public class SonarLintProjectConfiguration {

  private final List<SonarLintProperty> extraProperties = new ArrayList<>();
  private final List<ExclusionItem> fileExclusions = new ArrayList<>();
  @Nullable
  private EclipseProjectBinding projectBinding;
  private boolean autoEnabled = true;
  private boolean bindingSuggestionsDisabled = false;

  public List<ExclusionItem> getFileExclusions() {
    return fileExclusions;
  }

  public List<SonarLintProperty> getExtraProperties() {
    return extraProperties;
  }

  public boolean isBound() {
    return projectBinding != null;
  }

  public boolean isAutoEnabled() {
    return autoEnabled;
  }

  public void setAutoEnabled(boolean autoEnabled) {
    this.autoEnabled = autoEnabled;
  }

  public void setProjectBinding(@Nullable EclipseProjectBinding projectBinding) {
    this.projectBinding = projectBinding;
  }

  public Optional<EclipseProjectBinding> getProjectBinding() {
    return Optional.ofNullable(projectBinding);
  }

  public static class EclipseProjectBinding extends ProjectBinding {

    private final String connectionId;

    public EclipseProjectBinding(String connectionId, String projectKey, String sqPathPrefix, String idePathPrefix) {
      super(projectKey, sqPathPrefix, idePathPrefix);
      this.connectionId = connectionId;
    }

    public String connectionId() {
      return connectionId;
    }

    @Override
    public int hashCode() {
      final var prime = 31;
      var result = super.hashCode();
      result = prime * result + ((connectionId == null) ? 0 : connectionId.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!super.equals(obj)) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      var other = (EclipseProjectBinding) obj;
      return Objects.equals(connectionId, other.connectionId);
    }

  }

  public boolean isBindingSuggestionsDisabled() {
    return this.bindingSuggestionsDisabled;
  }

  public void setBindingSuggestionsDisabled(boolean bindingSuggestionsDisabled) {
    this.bindingSuggestionsDisabled = bindingSuggestionsDisabled;
  }

}
