class AddRoutesIdColumnToStation < ActiveRecord::Migration
  def change
    add_column :stations, :routes_id, :integer
  end
end
